package gg;

public class CardNotReadyException extends RuntimeException {

	public CardNotReadyException(String reason) {
		super(reason);
	}
}
