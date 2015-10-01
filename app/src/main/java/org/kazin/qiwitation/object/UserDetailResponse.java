package org.kazin.qiwitation.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDetailResponse {

    private Integer result_code;
    private List<Balance> balances = new ArrayList<Balance>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The result_code
     */
    public Integer getResult_code() {
        return result_code;
    }

    /**
     *
     * @param result_code
     * The result_code
     */
    public void setResult_code(Integer result_code) {
        this.result_code = result_code;
    }

    /**
     *
     * @return
     * The balances
     */
    public List<Balance> getBalances() {
        return balances;
    }

    /**
     *
     * @param balances
     * The balances
     */
    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}