package com.boletim.services;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.boletim.application.AlunoService;
import com.boletim.domain.Aluno;
import com.boletim.domain.BoletimNotas;
import com.boletim.domain.BoletimNotasAnual;
import com.boletim.domain.Disciplina;
import com.boletim.domain.Erro;
import com.boletim.domain.Nota;
import com.boletim.domain.TipoNota;
import com.boletim.services.stub.AlunoServiceBeanStub;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;

@RunWith(Arquillian.class)
@RunAsClient
public class AlunoResourceTest {

	private static final String RESOURCE_PREFIX = JaxRsActivator.class
			.getAnnotation(ApplicationPath.class).value().substring(1);
	private static final String WEBAPP_SRC = "src/main/webapp";
	
	@ArquillianResource
	URL deploymentUrl;
	
	@BeforeClass 
	public static void before() {
		RestAssured.filters(ResponseLoggingFilter.responseLogger(),
				new RequestLoggingFilter());
	}

	@Deployment(testable = false)
	public static WebArchive deployment() {
		String gson = "com.google.code.gson:gson";
		Collection<String> dependencies = new ArrayList<String>();
		dependencies.add(gson);
		File[] libs = Maven.resolver()
                .loadPomFromFile("pom.xml").resolve(dependencies)
                .withTransitivity().as(File.class);
		return ShrinkWrap.create(WebArchive.class, "services.war")
				.addClass(Aluno.class)
				.addClass(Nota.class)
				.addClass(Disciplina.class)
				.addClass(TipoNota.class)
				.addClass(Erro.class)
				.addClass(BoletimNotas.class)
				.addClass(BoletimNotasAnual.class)
				.addClass(AlunoResource.class)
				.addClass(AlunoService.class)
				.addClass(AlunoServiceBeanStub.class)
				.addClass(JaxRsActivator.class)
				.addClass(GsonMessageBodyHandler.class)
				.addAsLibraries(libs)
				.setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void deveriaConsultarAlunoExistentePorRa() throws Exception {
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/aluno/05008265");
        Response response = target.request().get();
        assertEquals(200, response.getStatus());
		String json = response.readEntity(String.class);
		String expectedJson = "{\"id\":1,\"nome\":\"Altieres de Matos\",\"ra\":\"05008265\"}";
        assertEquals(expectedJson, json);
	}
	
	@Test
	public void deveriaConsultarAlunoExistentePorRaComObjeto() throws Exception {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/aluno/05008265");
		Response response = target.request().get();
		assertEquals(200, response.getStatus());
		String json = response.readEntity(String.class);
		Gson gson = new Gson();
		Aluno aluno = gson.fromJson(json, Aluno.class);
		Aluno expectedAluno = getAluno("05008265");
		assertEquals(expectedAluno, aluno);
	}
	
	@Test
	public void deveriaConsultarAlunoInexistentePorRa() throws Exception {
		ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/aluno/05008266");
        Response response = target.request().get();
        assertEquals(200, response.getStatus());
        String json = response.readEntity(String.class);
        String expected = "{\"erro\":\"Aluno não encontrado para o RA: 05008266\"}";
		assertEquals(expected, json);
	}
	
	@Test
	public void deveriaConsultarAlunoInexistentePorRaComObjeto() throws Exception {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/aluno/05008266");
		Response response = target.request().get();
		assertEquals(200, response.getStatus());
		String json = response.readEntity(String.class);
		Gson gson = new Gson();
		Erro erro = gson.fromJson(json, Erro.class);
		Erro expectedErro = new Erro("Aluno não encontrado para o RA: 05008266");
		assertEquals(expectedErro, erro);
	}
	
	@Test
	public void deveriaConsultarNotasAluno() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/aluno/notas");
		Aluno aluno = getAluno("05008265");
		Response response = target.request().post(Entity.entity(aluno.getJson(), "application/json"));
		assertEquals(200, response.getStatus());
		String json = response.readEntity(String.class);
		Gson gson = new Gson();
		List<BoletimNotasAnual> boletimNotas = gson.fromJson(json, new TypeToken<List<BoletimNotasAnual>>(){}.getType());
		assertEquals(1, boletimNotas.size());
	}
	
	@Test
	public void deveriaConsultarNotasAlunoMaisDeUmaDisciplina() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/aluno/notas");
		Aluno aluno = getAluno("05008260");
		Response response = target.request().post(Entity.entity(aluno.getJson(), "application/json"));
		assertEquals(200, response.getStatus());
		String json = response.readEntity(String.class);
		Gson gson = new Gson();
		List<BoletimNotasAnual> boletimNotas = gson.fromJson(json, new TypeToken<List<BoletimNotasAnual>>(){}.getType());
		assertEquals(2, boletimNotas.size());
	}
	
	@Test
	public void deveriaConsultarNotasAlunoSemNotas() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/aluno/notas");
		Aluno aluno = getAluno("05008269");
		Response response = target.request().post(Entity.entity(aluno.getJson(), "application/json"));
		assertEquals(200, response.getStatus());
		String json = response.readEntity(String.class);
		Gson gson = new Gson();
		List<BoletimNotasAnual> boletimNotas = gson.fromJson(json, new TypeToken<List<BoletimNotasAnual>>(){}.getType());
		assertEquals(0, boletimNotas.size());
	}
	
	@Test
	public void deveriaConsultarNotasAlunoEValidarItens() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(deploymentUrl.toString() + RESOURCE_PREFIX + "/aluno/notas");
		Aluno aluno = getAluno("05008265");
		Response response = target.request().post(Entity.entity(aluno.getJson(), "application/json"));
		assertEquals(200, response.getStatus());
		String json = response.readEntity(String.class);
		Gson gson = new Gson();
		List<BoletimNotasAnual> boletimNotas = gson.fromJson(json, new TypeToken<List<BoletimNotasAnual>>(){}.getType());
		BoletimNotasAnual expectedBoletimNotas = getItemBoletim("Dispositivos Móveis");
		assertEquals(expectedBoletimNotas, boletimNotas.get(0));
	}

	private BoletimNotasAnual getItemBoletim(String disciplina) {
		BoletimNotasAnual expectedBoletimNotas = new BoletimNotasAnual();
		expectedBoletimNotas.setDisciplina(disciplina);
		expectedBoletimNotas.setNota1("10.0");
		expectedBoletimNotas.setNota2("10.0");
		expectedBoletimNotas.setNota3("10.0");
		expectedBoletimNotas.setNota4("10.0");
		expectedBoletimNotas.setNotaExame("10.0");
		return expectedBoletimNotas;
	}

	private Aluno getAluno(String ra) {
		Aluno aluno = new Aluno();
		aluno.setIdAluno(1L);
		aluno.setNome("Altieres de Matos");
		aluno.setRa(ra);
		return aluno;
	}
}
