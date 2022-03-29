package ru.stqa.pft.mantis.rest;

import java.util.Objects;

public class IssueRest {

    private int id;
    private String subject;
    private String description;
    private String state_name;

    public int getId() {
        return id;
    }

    public IssueRest withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public IssueRest withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IssueRest withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueRest issue = (IssueRest) o;
        return id == issue.id && Objects.equals(subject, issue.subject) && Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description);
    }

    public String getState_name() {
        return state_name;
    }

    public IssueRest withState_name(String state_name) {
        this.state_name = state_name;
        return this;
    }

}
