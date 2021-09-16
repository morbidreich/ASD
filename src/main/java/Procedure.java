import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="procedures")
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

    @Transient
    private boolean isVisible = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(name="procedure_fix",
            joinColumns = @JoinColumn(name="procedure_id"),
            inverseJoinColumns = @JoinColumn(name="fix_id"))
    //@OrderColumn(name = "id")
    //in order to perserve correct fix order i added id column to procedure_fix table
    //that's why it's important to add rows to procedure_fix in exact order
    //not the greatest solution, but works for now.
    //after loading fixes i can sort them using id column, hence annotation
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean visible) {
        isVisible = visible;
        setFixesVisibility(visible);
    }
    // for sid display all fixes but first/last (DER, TMA exit)
    // for star display all fixes but first (TMA entry)
    // TMA boundary related are doubled by 'TMA entry fixes' layer
    // and DER01/19 just looks plain bad
    private void setFixesVisibility(boolean visible) {
        for (int i = 1; i < fixList.size()-1; i ++) {
            fixList.get(i).setVisible(visible);
        }
        if (procedureType == ProcedureType.STAR)
            fixList.get(fixList.size()-1).setVisible(visible);
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
