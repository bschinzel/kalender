package de.schinzel.abwesenheit.model;

import java.io.Serializable;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "abwesenheit")
@NamedQueries({
	@NamedQuery(name="loescheAbwesenheit", 
			query="DELETE FROM Abwesenheit AS a WHERE a.uuid = :uuid"),
	@NamedQuery(name="alleAbwesenheitenEinerOrganisation", 
			query="SELECT a FROM Abwesenheit a WHERE a.organisationuuid = :organisationuuid"),
	@NamedQuery(name="alleAbwesenheitenEinerOrganisationEinesJahres", 
			query="SELECT a FROM Abwesenheit a WHERE a.organisationuuid = :organisationuuid AND FUNCTION('YEAR', a.datumstart) = :jahrbeginn"),
	@NamedQuery(name="alleAbwesenheitenEinerOrganisationEinesMonats", 
			query="SELECT a FROM Abwesenheit a WHERE a.organisationuuid = :organisationuuid AND FUNCTION('YEAR', a.datumstart) = :jahrbeginn AND FUNCTION('MONTH', a.datumstart) = :monatbeginn"),
	@NamedQuery(name="alleAbwesenheitenEinerOrganisationEinesDatums", 
			query="SELECT a FROM Abwesenheit a WHERE a.organisationuuid = :organisationuuid AND :datum BETWEEN a.datumstart AND a.datumende")
})
public class Abwesenheit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(columnDefinition = "CHAR")
	private String uuid;

	@Column(columnDefinition = "DATE")
	private LocalDate datumstart;
	
	@Column(columnDefinition = "DATE")
	private LocalDate datumende;
	
	@Column(columnDefinition = "TINYINT")
	private boolean genehmigt;

	@Column(columnDefinition = "VARCHAR")
	private String kommentar;

	@Column(columnDefinition = "CHAR")
	private String typuuid;
	
	@Column(columnDefinition = "CHAR")
	private String organisationuuid;

	@Column(columnDefinition = "CHAR")
	private String useruuid;
	
	@Version
	private int version;
	
	public Abwesenheit() {}
	
	public Abwesenheit(String uuid, String typuuid, String organisationuuid, String useruuid) {
		this.uuid = uuid;
		this.typuuid = typuuid;
		this.organisationuuid = organisationuuid;
		this.useruuid = useruuid;
	}

	public boolean getGenehmigt() {
		return this.genehmigt;
	}

	public void setGenehmigt(boolean genehmigt) {
		this.genehmigt = genehmigt;
	}

	public String getKommentar() {
		return this.kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		
		if (this.uuid != null) {
			throw new RuntimeException("Die UUID darf nicht mehrmals gesetzt werden.");
		}
		
		this.uuid = uuid;
	}

	public LocalDate getDatumstart() {
		return datumstart;
	}

	public void setDatumstart(LocalDate datumstart) {
		this.datumstart = datumstart;
	}

	public LocalDate getDatumende() {
		return datumende;
	}

	public void setDatumende(LocalDate datumende) {
		this.datumende = datumende;
	}

	public String getTypuuid() {
		return typuuid;
	}

	public void setTypuuid(String typuuid) {
		this.typuuid = typuuid;
	}

	public String getOrganisationuuid() {
		return organisationuuid;
	}

	public void setOrganisationuuid(String organisationuuid) {
		this.organisationuuid = organisationuuid;
	}

	public String getUseruuid() {
		return useruuid;
	}

	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public AbwesenheitDTO toDTO() {
		AbwesenheitDTO dto = new AbwesenheitDTO();
		dto.setUuid(this.uuid);
		dto.setDatumstart(this.datumstart.toString());
		dto.setDatumende(this.datumende.toString());
		dto.setGenehmigt(this.genehmigt);
		dto.setKommentar(this.kommentar);
		dto.setTypuuid(this.typuuid);
		dto.setUseruuid(this.useruuid);
		dto.setVersion(this.version);
		return dto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((datumende == null) ? 0 : datumende.hashCode());
		result = prime * result
				+ ((datumstart == null) ? 0 : datumstart.hashCode());
		result = prime * result + (genehmigt ? 1231 : 1237);
		result = prime * result
				+ ((kommentar == null) ? 0 : kommentar.hashCode());
		result = prime
				* result
				+ ((organisationuuid == null) ? 0 : organisationuuid.hashCode());
		result = prime * result + ((typuuid == null) ? 0 : typuuid.hashCode());
		result = prime * result
				+ ((useruuid == null) ? 0 : useruuid.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abwesenheit other = (Abwesenheit) obj;
		if (datumende == null) {
			if (other.datumende != null)
				return false;
		} else if (!datumende.equals(other.datumende))
			return false;
		if (datumstart == null) {
			if (other.datumstart != null)
				return false;
		} else if (!datumstart.equals(other.datumstart))
			return false;
		if (genehmigt != other.genehmigt)
			return false;
		if (kommentar == null) {
			if (other.kommentar != null)
				return false;
		} else if (!kommentar.equals(other.kommentar))
			return false;
		if (organisationuuid == null) {
			if (other.organisationuuid != null)
				return false;
		} else if (!organisationuuid.equals(other.organisationuuid))
			return false;
		if (typuuid == null) {
			if (other.typuuid != null)
				return false;
		} else if (!typuuid.equals(other.typuuid))
			return false;
		if (useruuid == null) {
			if (other.useruuid != null)
				return false;
		} else if (!useruuid.equals(other.useruuid))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "organisationuuid=" + this.organisationuuid + "; useruuid=" 
				+ this.useruuid + "; typuuid=" + this.typuuid + "; uuid=" + this.uuid + "; kommentar=" + this.kommentar
				+ "; genehmigt=" + this.genehmigt + "; datumstart=" + this.datumstart + "; datumende=" + this.datumende;
	}
}
