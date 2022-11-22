package tanulmanyitervezo.tervezo.model.ResponseModels;

import tanulmanyitervezo.tervezo.model.GradeHomework;
import tanulmanyitervezo.tervezo.model.GradeZH;
import tanulmanyitervezo.tervezo.model.Homework;
import tanulmanyitervezo.tervezo.model.ZH;

import java.util.ArrayList;
import java.util.List;

public class GradeResponseModel {
    private String subjectName;
    private long subjectId;
    private List<GradeZH> gradeZHList;
    private List<GradeHomework> gradeHomeworks;

    public GradeResponseModel(String subjectName, long subjectId){
        this.subjectName = subjectName;
        this.subjectId=subjectId;
        gradeZHList = new ArrayList<>();
        gradeHomeworks = new ArrayList<>();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<GradeZH> getGradeZHList() {
        return gradeZHList;
    }

    public void setGradeZHList(List<GradeZH> grades) {
        this.gradeZHList = grades;
    }

    public void addZHWithoutPoints(ZH zh){
        GradeZH newGrade = new GradeZH();
        newGrade.setZh(zh);
        newGrade.setPoints(0);
        gradeZHList.add(newGrade);
    }

    public void addGradeZH(GradeZH grade){
        grade.setUser(null);
        gradeZHList.add(grade);
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public void addGradeHomework(GradeHomework grade){
        grade.setUser(null);
        gradeHomeworks.add(grade);
    }

    public List<GradeHomework> getGradeHomeworks() {
        return gradeHomeworks;
    }

    public void setGradeHomeworks(List<GradeHomework> gradeHomeworks) {
        this.gradeHomeworks = gradeHomeworks;
    }

    public void addHomeworkWithoutPoints(Homework hw){
        GradeHomework newGrade = new GradeHomework();
        newGrade.setHomework(hw);
        newGrade.setPoints(0);
        gradeHomeworks.add(newGrade);
    }
}
