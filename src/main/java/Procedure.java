import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="procedure_name")
    private String procedureName;

    @Enumerated
    private Runway runway;

    @Enumerated
    @Column(name="procedure_type")
    private ProcedureType procedureType;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(name="procedure_fix",
            joinColumns = @JoinColumn(name="procedure_id"),
            inverseJoinColumns = @JoinColumn(name="fix_id"))
    private List<Fix> fixList;

    public Procedure() {
    }

    public Procedure(String procedureName, Runway runway, ProcedureType procedureType) {
        this.procedureName = procedureName;
        this.runway = runway;
        this.procedureType = procedureType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public ProcedureType getProcedureType() {
        return procedureType;
    }

    public void setProcedureType(ProcedureType procedureType) {
        this.procedureType = procedureType;
    }

    public List<Fix> getFixList() {
        return fixList;
    }

    public void setFixList(List<Fix> fixList) {
        this.fixList = fixList;
    }

    public void addFix(Fix fix) {
        if (fixList == null)
            fixList = new ArrayList<>();
        fixList.add(fix);
    }

    @Override
    public String toString() {
        return "Procedure: [id=" + id + ", name=" + procedureName + ", runway=" + runway + ", type=" + procedureType + "]";
    }
}
