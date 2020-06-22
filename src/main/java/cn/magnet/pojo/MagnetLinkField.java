package cn.magnet.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.lang.annotation.Documented;
import java.util.Date;

/**
 * 2020/6/15 12:45
 *
 * @author Evan Ma
 * @since
 **/
@Document(indexName = "magnetlink",type = "MagnetLinkField")
public class MagnetLinkField {
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
    @Field(index = true,store = true,type = FieldType.Long)
    private Long pid;

    @Field(index = true,store = true,type = FieldType.Text)
    private String magnetLinkFileSize;

    @Field(index = true,store = true,type = FieldType.Date)
    private Date crawledDate;

    @Field(index = true,store = true,type = FieldType.Text)
    private String fromWhichSe;

    @Field(index = true,store = true,type = FieldType.Text)
    private String magnetLink;

    @Field(index = true,store = true,type = FieldType.Integer)
    private int magnetDownloadTimes;

    @Field(index = true,store = true,type = FieldType.Float)
    private Float magnetLinkSize;

    @Field(index = true,store = true, analyzer = "ik_smart", searchAnalyzer = "ik_smart",type = FieldType.Text)
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