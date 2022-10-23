package pojo;

public class Label {
    private int labelId;// 标签ID
    private String labelName;// 标签名称
    private String labelDescription = "";// 标签描述


    public Label() {
    }

    public Label(String labelName) {
        this.labelName = labelName;
    }

    public Label(String labelName, String labelDescription) {
        this.labelName = labelName;
        this.labelDescription = labelDescription;
    }

    public Label(int labelId, String labelName, String labelDescription) {
        this.labelId = labelId;
        this.labelName = labelName;
        this.labelDescription = labelDescription;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelDescription() {
        return labelDescription;
    }

    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelId=" + labelId +
                ", labelName='" + labelName + '\'' +
                ", labelDescription='" + labelDescription + '\'' +
                '}';
    }
}
