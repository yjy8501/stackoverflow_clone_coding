package com.preproject.overflow.question.entity;

import com.preproject.overflow.audit.Auditable;
import com.preproject.overflow.member.entity.Member;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arraylist;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    private int voteCount;

    private int answerCount;

    private int viewCount;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionTag> questionTagList = new ArrayList<>();

    // 연관 관계 메서드
    public void setMember(Member member) {
        this.member = member;
    }

    public void addQuestionTag(QuestionTag questionTag) {
        this.questionTagList.add(questionTag);
        if(questionTag.getQuestion() != this){
            questionTag.addQuestion(this);
        }
    }

    public void addAnswer(Answer answer) {
        this.answerList.add(answer);
        if(answer.getQuestion() != this){
            answer.setQuestion(this);
        }
    }

}
