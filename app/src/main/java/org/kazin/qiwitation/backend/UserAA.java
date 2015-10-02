package org.kazin.qiwitation.backend;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Alexey on 02.10.2015.
 */
@Table(name = "Users")
public class UserAA extends Model {

    @Column(name = "Name")
    public String name;

    @Column (name = "UserId")
    public int UserId;

    public UserAA() {
        super();
    }

}
