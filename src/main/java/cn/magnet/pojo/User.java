package cn.magnet.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * 2020/6/19 15:10
 *
 * @author Evan Ma
 * @since
 **/
@Entity
@Table(name = "user")
public class User {
    /**
     * uid	int	11
     * uname	varchar	45
     * upassword	varchar	20
     * ubirthday	date	0
     * urank	int	3
     * ucreatedate	date	0
     * uphone	varchar	30
     * uemail	varchar	40
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uId;

    @Column(name = "uname")
    private String uName;

    @Column(name = "upassword")
    private String uPassword;

    @Column(name = "ubirthday")
    private Date uBirthday;

    @Column(name = "urank")
    private int uRank;

    @Column(name = "ucreatedate")
    private Date uCreateDate;

    @Column(name = "uphone")
    private String uPhone;

    @Column(name = "uemail")
    private String uEmail;

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public Date getuBirthday() {
        return uBirthday;
    }

    public void setuBirthday(Date uBirthday) {
        this.uBirthday = uBirthday;
    }

    public int getuRank() {
        return uRank;
    }

    public void setuRank(int uRank) {
        this.uRank = uRank;
    }

    public Date getuCreateDate() {
        return uCreateDate;
    }

    public void setuCreateDate(Date uCreateDate) {
        this.uCreateDate = uCreateDate;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uName='" + uName + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uBirthday=" + uBirthday +
                ", uRank=" + uRank +
                ", uCreateDate=" + uCreateDate +
                ", uPhone='" + uPhone + '\'' +
                ", uEmail='" + uEmail + '\'' +
                '}';
    }
}
