public class StudentRecord {
    private String sid;
    private float midterm;
    private float assignments;
    private float finalExam;
    private double finalMark;
    private String letterGrade;

    public StudentRecord() {}

    public StudentRecord (String sid, float midterm, float assignments, float finalExam) {
        this.sid = sid;
        this.midterm = midterm;
        this.assignments = assignments;
        this.finalExam = finalExam;
        this.finalMark = (assignments * 0.2) + (midterm * 0.3) + (finalExam * 0.5);
        this.letterGrade = "";
    }

    public String getSid() {
        return this.sid;
    }

    public float getMidterm() {
        return this.midterm;
    }

    public float getAssignments() {
        return this.assignments;
    }

    public float getFinalExam() {
        return this.finalExam;
    }

    public double getFinalMark() {
        return this.finalMark;
    }

    public String getLetterGrade() {
        if (this.finalMark >= 80) {
            return "A";
        }
        else if (this.finalMark >= 70 && this.finalMark < 80) {
            return "B";
        }
        else if (this.finalMark >= 60 && this.finalMark < 70) {
            return "C";
        }
        else if (this.finalMark >= 50 && this.finalMark < 60) {
            return "D";
        }
        else if (this.finalMark < 50 ) {
            return "F";
        }
        else {
            return "N/A";
        }
    }
}