/*
package br.akls.javaspeech.sintetizer;

import javax.speech.*;
import javax.speech.synthesis.*;
 

public class Sintetizador {
 
	static Synthesizer synthesizer;
	static EngineListener engineListener = new EngineAdapter() {
		public void engineError(EngineErrorEvent e) {
			System.out.println(
				"Engine error: " + e.getEngineError().getMessage());
		}
	};	

	public Sintetizador() {
		try {
			synthesizer = Central.createSynthesizer(null);
			if (synthesizer != null) {
				synthesizer.allocate();
				synthesizer.addEngineListener(engineListener);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void speak(String s) {
		if (synthesizer != null) {
			try {
				synthesizer.speak(s, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(s);
		} 
	}
	
	public static void main(String args[]) {
		Sintetizador vp = new Sintetizador();
		String texto = " <JSML> "
			+ "<BREAK MSECS=\"300\"/>"
			+ "<PROS PITCH=\"80\" RANGE=\"50\" RATE=\"150\">"
			+ "Testando o Sintetizador."
			+ "</PROS>"
			+ " </JSML> ";
		Sintetizador.speak(texto);
	}

}
*/