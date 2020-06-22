package cn.magnet.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * 2020/6/15 12:45
 *
 * @author Evan Ma
 * @since
 **/
@Entity
@Table(name = "pages")
public class MagnetLink {
    /**
     *   `pid` INT NOT NULL AUTO_INCREMENT,
     *   `pmagnetlink_file_size` FLOAT NULL,
     *   `pcrawled_date` DATE NULL,
     *   `pfrom_which_se` LONGTEXT NULL,
     *   `pmagnet_link` VARCHAR(200) NULL,
     *   `pmagnet_download_times` INT NULL,
     *   `pmagnetlink_size` FLOAT NULL,
     *   //crawledDate  爬取的时间---发布时间
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "pmagnetlink_file_size")
    private String magnetLinkFileSize;

    @Column(name = "pcrawled_date")
    private Date crawledDate;

    @Column(name = "pfrom_which_se")
    private String fromWhichSe;

    @Column(name = "pmagnet_link")
    private String magnetLink;

    @Column(name = "pmagnet_download_times")
    private int magnetDownloadTimes;

    @Column(name = "pmagnetlink_size")
    private Float magnetLinkSize;

    @Column(name = "pmagnetlink_key")
    private String magnetLinkKey;


    public String getMagnetLinkKey() {
        return magnetLinkKey;
    }

    public void setMagnetLinkKey(String magnetLinkKey) {
        this.magnetLinkKey = magnetLinkKey;
    }


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getMagnetLinkFileSize() {
        return magnetLinkFileSize;
    }

    public void setMagnetLinkFileSize(String magnetLinkFileSize) {
        this.magnetLinkFileSize = magnetLinkFileSize;
    }

    public Float getMagnetLinkSize() {
        return magnetLinkSize;
    }

    public void setMagnetLinkSize(Float magnetLinkSize) {
        this.magnetLinkSize = magnetLinkSize;
    }

    public Date getCrawledDate() {
        return crawledDate;
    }

    public void setCrawledDate(Date crawledDate) {
        this.crawledDate = crawledDate;
    }

    public String getFromWhichSe() {
        return fromWhichSe;
    }

    public void setFromWhichSe(String fromWhichSe) {
        this.fromWhichSe = fromWhichSe;
    }

    public String getMagnetLink() {
        return magnetLink;
    }

    public void setMagnetLink(String magnetLink) {
        this.magnetLink = magnetLink;
    }

    public int getMagnetDownloadTimes() {
        return magnetDownloadTimes;
    }

    public void setMagnetDownloadTimes(int magnetDownloadTimes) {
        this.magnetDownloadTimes = magnetDownloadTimes;
    }

    @Override
    public String toString() {
        return "MagnetLink{" +
                "pid=" + pid +
                ", magnetLinkFileSize=" + magnetLinkFileSize +
                ", crawledDate=" + crawledDate +
                ", fromWhichSe='" + fromWhichSe + '\'' +
                ", magnetLink='" + magnetLink + '\'' +
                ", magnetDownloadTimes=" + magnetDownloadTimes +
                ", magnetLinkSize=" + magnetLinkSize +
                ", magnetLinkKey='" + magnetLinkKey + '\'' +
                '}';
    }

// 对应字段还有getter/setter  toString
}