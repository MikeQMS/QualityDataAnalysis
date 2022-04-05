package com.example.qdata.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Data implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate = new Date();

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate = new Date();

    private long meldung;
    private LocalDate stoerBeginn;
    private String art;
    private String mat;
    private String matKurzText;
    private String positionText;
    private String beschreibung;
    private int baumuster;
    private int verantwortlicher;
    private int arbeitsplatz;
    private int massnahmenCode;
    private String massnahmenText;
    private String refNumber;
    private String createdBy;
    private LocalDate taskClosed;
    private String taskClosedBy;
    private int werksnummer;
    private int ursache;
    private String ursachenNummer;
    private String internalWorkplace;
    private String pruefling;
    private String cgrprobl;
    private String scd;
    private int positionNumber;
    private int ortnumber;
    private String baugruppe;
    private double price;

    public Data(long meldung, LocalDate stoerBeginn, String art, String mat, String matKurzText, String positionText, String beschreibung, int baumuster, int verantwortlicher, int arbeitsplatz, int massnahmenCode, String massnahmenText, String refNumber, String createdBy, LocalDate taskClosed, String taskClosedBy, int werksnummer, int ursache, String ursachenNummer, String internalWorkplace, String pruefling, String cgrprobl, String scd, int positionNumber, int ortnumber, String baugruppe, double price) {
        this.meldung = meldung;
        this.stoerBeginn = stoerBeginn;
        this.art = art;
        this.mat = mat;
        this.matKurzText = matKurzText;
        this.positionText = positionText;
        this.beschreibung = beschreibung;
        this.baumuster = baumuster;
        this.verantwortlicher = verantwortlicher;
        this.arbeitsplatz = arbeitsplatz;
        this.massnahmenCode = massnahmenCode;
        this.massnahmenText = massnahmenText;
        this.refNumber = refNumber;
        this.createdBy = createdBy;
        this.taskClosed = taskClosed;
        this.taskClosedBy = taskClosedBy;
        this.werksnummer = werksnummer;
        this.ursache = ursache;
        this.ursachenNummer = ursachenNummer;
        this.internalWorkplace = internalWorkplace;
        this.pruefling = pruefling;
        this.cgrprobl = cgrprobl;
        this.scd = scd;
        this.positionNumber = positionNumber;
        this.ortnumber = ortnumber;
        this.baugruppe = baugruppe;
        this.price = price;
    }

    public Data(long meldung, LocalDate stoerBeginn) {
        this.meldung = meldung;
        this.stoerBeginn = stoerBeginn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return meldung == data.meldung && baumuster == data.baumuster && verantwortlicher == data.verantwortlicher && arbeitsplatz == data.arbeitsplatz && massnahmenCode == data.massnahmenCode && werksnummer == data.werksnummer && ursache == data.ursache && positionNumber == data.positionNumber && ortnumber == data.ortnumber && Double.compare(data.price, price) == 0 && Objects.equals(stoerBeginn, data.stoerBeginn) && Objects.equals(art, data.art) && Objects.equals(mat, data.mat) && Objects.equals(matKurzText, data.matKurzText) && Objects.equals(positionText, data.positionText) && Objects.equals(beschreibung, data.beschreibung) && Objects.equals(massnahmenText, data.massnahmenText) && Objects.equals(refNumber, data.refNumber) && Objects.equals(createdBy, data.createdBy) && Objects.equals(taskClosed, data.taskClosed) && Objects.equals(taskClosedBy, data.taskClosedBy) && Objects.equals(ursachenNummer, data.ursachenNummer) && Objects.equals(internalWorkplace, data.internalWorkplace) && Objects.equals(pruefling, data.pruefling) && Objects.equals(cgrprobl, data.cgrprobl) && Objects.equals(scd, data.scd) && Objects.equals(baugruppe, data.baugruppe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meldung, stoerBeginn, mat, matKurzText, positionText, beschreibung, baumuster, verantwortlicher, arbeitsplatz, massnahmenCode, massnahmenText, refNumber, createdBy, taskClosed, taskClosedBy, werksnummer, ursache, ursachenNummer, internalWorkplace, pruefling, cgrprobl, scd, positionNumber, ortnumber, baugruppe, price);
    }

}
