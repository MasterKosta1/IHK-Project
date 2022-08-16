package com.example.beispielprojekt;

public class User {
	

    private String vorname;
    private String nachname;
    private String alter;
    private String geschlecht;
    private String beruf;
    private String email;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getAlter() {
		return alter;
	}

	public void setAlter(String alter) {
		this.alter = alter;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	public void setBeruf(String beruf) {
		this.beruf = beruf;
	}

	public String getBeruf() {
        return beruf;
    }


}
