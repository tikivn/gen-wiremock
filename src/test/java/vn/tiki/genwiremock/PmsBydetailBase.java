package vn.tiki.genwiremock;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
abstract public class PmsBydetailBase {

    @MockBean
    AerospikeClient aerospikeClient;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void before() {
        Key[] keys = new Key[]{
                new Key("product", "changes", "11"),
                new Key("product", "changes", "22"),
                new Key("product", "changes", "33"),
                new Key("product", "changes", "44"),
        };

        Record[] res = new Record[]{
                new Record(Map.ofEntries(
                        Map.entry("brand", 25422L),
                        Map.entry("model", "XS-11")
                ), 0, 0),
                new Record(Map.ofEntries(
                        Map.entry("brand", 25422L),
                        Map.entry("model", "XS-11")
                ), 0, 0),
                new Record(Map.ofEntries(
                        Map.entry("brand", 25422L),
                        Map.entry("model", "XS-11")
                ), 0, 0),
                new Record(Map.ofEntries(
                        Map.entry("brand", 25422L),
                        Map.entry("model", "XS-11")
                ), 0, 0)
        };
        when(aerospikeClient.get(null, keys)).thenReturn(res);
    }

    public void doubleCheck(double actual, double expect) {
        assertThat(actual, closeTo(expect, 0.01));
    }

}
