package tanulmanyitervezo.tervezo.Models;

public class TimetableResponseModel {
    private String subjectName;
    private Period period;

    public TimetableResponseModel(){}
    public TimetableResponseModel(String name, Period period){
        subjectName=name;
        this.period=period;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
