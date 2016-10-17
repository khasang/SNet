package io.khasang.snet.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(length = 1255)
    private String question;

    @Column(length = 1255)
    private String correectAnswers;

    @Column(length = 1255)
    private String answer1;

    @Column(length = 1255)
    private String answer2;

    @Column(length = 1255)
    private String answer3;

    @Column(length = 1255)
    private String answer4;

       public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorreectAnswers() {
        return correectAnswers;
    }

    public void setCorreectAnswers(String correectAnswers) {
        this.correectAnswers = correectAnswers;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

}
