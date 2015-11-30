import java.util.Date;
import java.util.List;
import java.util.Map;

public class ScenarioBean {
    private String name;
    private Date date;
    private List<Map<String, Object>> flows;
    private String consumer;
    private String producer;
    private int start;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Map<String, Object>> getFlows() {
        return flows;
    }

    public void setFlows(List<Map<String, Object>> flows) {
        this.flows = flows;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
