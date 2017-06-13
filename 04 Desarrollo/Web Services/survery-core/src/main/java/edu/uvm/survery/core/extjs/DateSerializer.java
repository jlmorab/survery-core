package edu.uvm.survery.core.extjs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateSerializer extends JsonSerializer<Date>{

	private final static String FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
		DateFormat formatter = new SimpleDateFormat(FORMAT);
		String formattedDate = formatter.format(value);
		gen.writeString(formattedDate);
	}
}
