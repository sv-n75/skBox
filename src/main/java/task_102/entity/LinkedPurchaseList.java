package task_102.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@Table(name = "Linked_purchase_list")
public class LinkedPurchaseList implements Serializable {

    @EmbeddedId
    private Id id;

    @Column(name = "student_id", updatable = false, insertable = false)
    private int studentId;

    @Column(name = "course_id", updatable = false, insertable = false)
    private int courseId;

    public LinkedPurchaseList() {
    }

    public LinkedPurchaseList(Id id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Embeddable
    @Data
    public static class Id implements Serializable {
        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;

        public Id(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }
    }

}
