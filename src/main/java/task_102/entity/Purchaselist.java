package task_102.entity;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@Table(name = "Purchaselist")
public class Purchaselist implements Serializable {

    @EmbeddedId
    Id id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Course course;


    @Column(name = "price")
    private int price;

    @Column(name = "subscription_date")
    private Date purchaseSubscriptionDate;

    public Purchaselist() {
    }

    public Purchaselist(Id id, Student student, Course course, int price, Date purchaseSubscriptionDate) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.price = price;
        this.purchaseSubscriptionDate = purchaseSubscriptionDate;
    }

    @Override
    public String toString() {
        return "Purchaselist{" +
                "student=" + student.getName() +
                ", course=" + course.getName() +
                '}';
    }

    @Embeddable
    @Data
    public static class Id implements Serializable {
        @Column(name = "student_name")
        String studentName;

        @Column(name = "course_name")
        String courseName;

        public Id() {
        }

        public Id(String studentName, String courseName) {
            this.studentName = studentName;
            this.courseName = courseName;
        }
    }


}


