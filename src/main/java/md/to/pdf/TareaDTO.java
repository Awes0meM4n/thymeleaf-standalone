package md.to.pdf;

import java.time.Instant;
import java.util.Date;

public class TareaDTO {

	public enum Estado {
		CADUCADA("caducada"), PROXIMA_CADUCAR("proxima-caducar"), CON_TIEMPO("con tiempo");

		private final String texto;

		Estado(String texto) {
			this.texto = texto;
		}

		public String getTexto() {
			return texto;
		}
	};

	public String titulo, tablero, asignado, responsable, enlace;
	public Date fecha;
	public boolean prioritaria;
	public Estado estado;

	public TareaDTO(String titulo, String tablero, Date fecha, String asignado, String responsable, String enlace,
			boolean prioritaria, Estado estado) {
		super();
		this.titulo = titulo;
		this.tablero = tablero;
		this.fecha = fecha;
		this.asignado = asignado;
		this.responsable = responsable;
		this.enlace = enlace;
		this.prioritaria = prioritaria;
		this.estado = estado;
	}

}
