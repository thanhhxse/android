package thanhhx.d.employee_management;

import java.io.Serializable;
import java.util.Date;

public class TaskDTO implements Serializable {
    private int Id;
    private String taskName;
    private String prevAssigner;
    private String taskContent;
    private String manaCom;
    private int manaMark;
    private Date startDate;
    private Date endDate;
    private int Status;
    private Date createDate;
    private int createdByEmp;
    private boolean confirmation;

}
