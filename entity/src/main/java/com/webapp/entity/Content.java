package com.webapp.entity;

import com.webapp.entity.exceptions.EmptyTextException;
import com.webapp.entity.exceptions.TextLengthOverLimitException;

public class Content {
    private String text;

    private Content(String text) { 
        this.text = text;
    }
    
    /**
     * Método que inicializa o conteúdo com o texto fornecido.
     *
     * @param text o texto do conteúdo
     * @throws TextLengthOverLimitException se o texto tiver mais de 100 caracteres
     * @throws EmptyTextException se o texto estiver vazio
     */
    public static Content create(String text) throws TextLengthOverLimitException, EmptyTextException {
        if (text.isEmpty()) {
            throw new EmptyTextException("A anotação não pode ser vazia");
        }
        if (text.length() > 100) {
            throw new TextLengthOverLimitException("O conteúdo da anotação deve ter até 100 caracteres");
        }
        return new Content(text);
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return getText();
    }
}
