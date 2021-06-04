
package acme.components.Spam;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.spam.Spam;
import acme.entities.spam.SpamWord;
import acme.framework.helpers.StringHelper;

public class Spam1 {

	@Autowired
	public AnonymousSpamRepository spamRepository;


	protected Spam1(final AnonymousSpamRepository spamRepository) {
		this.spamRepository = spamRepository;
	}

	public static boolean isSpam(final String text, final List<Spam> spam) {
		if (StringHelper.isBlank(text) || spam.isEmpty()) {
			return false;
		}

		boolean salida = true;
		final StringTokenizer st = new StringTokenizer(text);

		String texto = text;
		Double contador = 0.0;

		final List<SpamWord> palabras = spam.get(0).getSpamWords().stream().collect(Collectors.toList());
		for (int i = 0; i < palabras.size(); i++) {
			final String palabra = palabras.get(i).getWord();

			while (texto.indexOf(palabra) > -1) {
				texto = texto.substring(texto.indexOf(palabra) + palabra.length(), texto.length());
				contador++;
			}
		}

		if ((contador / st.countTokens()) <= spam.get(0).getThreshold()) {
			salida = false;
		}

		return salida;
	}

}
