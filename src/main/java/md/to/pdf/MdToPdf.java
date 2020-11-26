package md.to.pdf;

import java.io.File;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;
import md.to.pdf.TareaDTO.Estado;

public class MdToPdf {

	public static void main(String[] args) {
		String rol = "SECOG";
		String salida = "resultado.pdf";
		try (PrintWriter printer = new PrintWriter(new File(salida))) {
			List<TareaDTO> tareas = Arrays.asList(new TareaDTO[]{
				new TareaDTO("titulo1", "tablero", Date.from(Instant.parse("2020-11-21T00:00:00.00Z")),
						"SECOG", "responsable", "http://10.71.92.138:8085/tareas/1", true, Estado.CADUCADA),
				new TareaDTO("titulo2", "tablero", Date.from(Instant.parse("2020-11-21T00:00:00.00Z")),
						"SECAM", "responsable", "http://10.71.92.138:8085/tareas/2", true, Estado.CADUCADA)
			});
	        
	        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
	        resolver.setTemplateMode("HTML5");
	        resolver.setSuffix(".html");
	        Locale locale=Locale.getDefault();
	        TemplateEngine templateEngine = new TemplateEngine();
	        templateEngine.setTemplateResolver(resolver);
	        Context context = new Context(locale);
	        context.setVariable("rol", rol);
	        // en cada linea pon el restultado de los filtros que tienes hechos ya
	        context.setVariable("prioritarias", tareas);
	        context.setVariable("caducadas", tareas);
	        context.setVariable("proximas", tareas);
	        String html = templateEngine.process("informe", context);
	        
	        // Para ver el HTML y que Rafa te lo ponga bonito
//	        PrintWriter printerHtml = new PrintWriter("resultado.html");
//	        printerHtml.write(html);
//	        printerHtml.close();
			
			HtmlToPdf.create()
				    .object(HtmlToPdfObject.forHtml(html))
				    .convert(salida);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
