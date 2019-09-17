package me.fautor.punishmenthistory.enums;

/**
 * Placeholders.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public enum Placeholders {

    PLAYER_NAME("%player_name%"),
    LINE("%line%");

    private String placeholder;

    Placeholders(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

}
