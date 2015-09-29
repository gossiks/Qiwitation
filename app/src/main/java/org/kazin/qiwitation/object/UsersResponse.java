package org.kazin.qiwitation.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersResponse {

    private Integer result_code;
    private List<User> users = new ArrayList<User>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private String message;

    /**
     *
     * @return
     * The result_code
     */
    public Integer getResultCode() {
        return result_code;
    }

    /**
     *
     * @param result_code
     * The result_code
     */
    public void setResultCode(Integer resultCode) {
        this.result_code = resultCode;
    }

    /**
     *
     * @return
     * The users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     * The users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /*
     * @return
     * The message
     */

    public String getMessage() {
        return message;
    }

    /*
     * @param message
     * The message
     */

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}