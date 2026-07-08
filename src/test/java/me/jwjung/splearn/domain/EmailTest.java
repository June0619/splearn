package me.jwjung.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class EmailTest {
    @Test
    void equality() {
        var email1 = new Email("jiwoon@splearn.app");
        var email2 = new Email("jiwoon@splearn.app");

        assertThat(email1).isEqualTo(email2);
    }

}