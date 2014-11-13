package com.boletim.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

import com.google.gson.Gson;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GsonMessageBodyHandler implements MessageBodyReader<Object>,
		MessageBodyWriter<Object> {

	private @Context Providers providers;
	
	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		InputStreamReader streamReader = new InputStreamReader(entityStream,
				"ISO-8859-1");
		try {
			Type jsonType;
			if (type.equals(genericType)) {
				jsonType = genericType;
			} else {
				jsonType = type;
			}
			Gson gson = new Gson();
			return gson.fromJson(streamReader, jsonType);
		} finally {
			streamReader.close();
		}
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(Object t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(Object t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		OutputStreamWriter writer = new OutputStreamWriter(entityStream,
				"ISO-8859-1");
		try {
			Type jsonType;
			if (type.equals(genericType)) {
				jsonType = genericType;
			} else {
				jsonType = type;
			}
			Gson gson = new Gson();
			gson.toJson(t, jsonType, writer);
		} finally {
			writer.close();
		}
	}

}
