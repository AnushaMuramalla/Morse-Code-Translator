package com.example.morsecodeconverter;

public class Letter {
    private String letter;           // The letter (e.g., "A")
    private String morseCode;        // The Morse code representation (e.g., ".-")
    private int soundResourceId;     // The resource ID for the associated Morse code sound file

    /**
     * Constructor for the Letter class.
     *
     * @param letter          The letter (e.g., "A").
     * @param morseCode       The Morse code representation (e.g., ".-").
     * @param soundResourceId The resource ID for the sound file (e.g., R.raw.morse_a).
     */
    public Letter(String letter, String morseCode, int soundResourceId) {
        this.letter = letter;
        this.morseCode = morseCode;
        this.soundResourceId = soundResourceId;
    }

    /**
     * Get the letter.
     *
     * @return The letter.
     */
    public String getLetter() {
        return letter;
    }

    /**
     * Get the Morse code representation.
     *
     * @return The Morse code.
     */
    public String getMorseCode() {
        return morseCode;
    }

    /**
     * Get the resource ID for the sound file.
     *
     * @return The sound resource ID.
     */
    public int getSoundResourceId() {
        return soundResourceId;
    }
}
