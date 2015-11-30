import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class ScenarioDAO {
    public static void main(String[] args) throws JsonProcessingException {
        Settings.Builder settings = Settings.builder();
        Path dataPath = FileSystems.getDefault().getPath("data");
        settings.put("cluster.name", "index");
        settings.put("http.port", 9200);
        settings.put("network.host","localhost");
        settings.put("transport.tcp.port", 9300);
        settings.put("path.home", dataPath.toFile().getAbsolutePath());
        settings.put("path.data", dataPath.toFile().getAbsolutePath());
        settings.build();
        Node esNode = NodeBuilder.nodeBuilder().settings(settings).node();
        Client esClient = esNode.client();
        ObjectMapper mapper = new ObjectMapper();


        // Create index
        try {
            esClient.admin().indices().prepareCreate("esbungie").execute().actionGet();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ScenarioBean scenario = new ScenarioBean();
        scenario.setName("Awesome scenario");
        scenario.setDate(new Date());
        Map<String, Object> flow = new HashMap<String, Object>();
        scenario.setConsumer("1");
        scenario.setProducer("3");
        scenario.setStart(5);
        List<Map<String, Object>> flows = new ArrayList<Map<String, Object>>();
        flows.add(flow);
        scenario.setFlows(flows);
        byte[] json = mapper.writeValueAsBytes(scenario);

        IndexResponse response = esClient.prepareIndex("esbungie", "scenario", "1")
                .setSource(json)
                .get();

        System.out.println(response);
        while(true);
        //esNode.close();
    }
}
