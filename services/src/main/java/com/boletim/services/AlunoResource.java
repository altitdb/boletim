package com.boletim.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.boletim.application.AlunoService;
import com.boletim.domain.Aluno;
import com.boletim.domain.BoletimNotasAnual;
import com.boletim.domain.Erro;
import com.boletim.domain.Nota;

@Path("/aluno")
public class AlunoResource {

	@EJB
	private AlunoService alunoService;

	@GET
	@Path("/{ra}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=iso-8859-1")
	public Object findByRa(@PathParam("ra") String ra) {
		Aluno aluno = alunoService.consultar(ra);
		if (aluno == null) {
			Erro erro = new Erro("Aluno não encontrado para o RA: " + ra);
			return erro;
		} 
		return aluno;
	}

	@POST
	@Path("/notas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + ";charset=iso-8859-1")
	public Object findNotas(Aluno aluno) {
		List<Nota> notas = alunoService.consultarNotas(aluno.getRa());
		Map<Long, BoletimNotasAnual> map = new HashMap<Long, BoletimNotasAnual>();
		for (Nota nota : notas) {
			BoletimNotasAnual b = map.get(nota.getDisciplina().getIdDisciplina());
			if (b == null) {
				b = new BoletimNotasAnual();
				b.setDisciplina(nota.getDisciplina().getNome());
				map.put(nota.getDisciplina().getIdDisciplina(), b);
			}
			switch (nota.getTipo()) {
			case BIM_1:
				b.setNota1(formatNota(nota.getNota()));
				break;
			case BIM_2:
				b.setNota2(formatNota(nota.getNota()));
				break;
			case BIM_3:
				b.setNota3(formatNota(nota.getNota()));
				break;
			case BIM_4:
				b.setNota4(formatNota(nota.getNota()));
				break;
			case EXAME:
				b.setNotaExame(formatNota(nota.getNota()));
				break;
			}
		}
		return map.values();
	}
	
	private String formatNota(BigDecimal nota) {
		if (nota == null) {
			nota = BigDecimal.ZERO;
		}
		Locale locale = new Locale("pt", "BR");
		DecimalFormat format = new DecimalFormat("#0.0");
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
		symbols.setDecimalSeparator('.');
		format.setDecimalFormatSymbols(symbols);
		return format.format(nota);
	}
	
}