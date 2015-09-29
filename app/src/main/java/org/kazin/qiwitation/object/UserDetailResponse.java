package org.kazin.qiwitation.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDetailResponse {

    private Integer resultCode;
    private List<Balance> balances = new ArrayList<Balance>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The resultCode
     */
    public Integer getResultCode() {
        return resultCode;
    }

    /**
     *
     * @param resultCode
     * The result_code
     */
    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
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