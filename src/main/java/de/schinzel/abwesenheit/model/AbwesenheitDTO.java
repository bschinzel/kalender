package de.schinzel.abwesenheit.model;

import java.io.Serializable;
import java.time.LocalDate;

public class AbwesenheitDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private String datumstart;
	private String datumende;
	private boolean genehmigt;
	private String kommentar;
	private String typuuid;
	private String useruuid;
	private int version;
	
	public AbwesenheitDTO() {}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDatumstart() {
		return datumstart;
	}

	public void setDatumstart(String datumstart) {
		this.datumstart = datumstart;
	}

	public String getDatumende() {
		return datumende;
	}

	public void setDatumende(String datumende) {
		this.datumende = datumende;
	}

	public boolean isGenehmigt() {
		return genehmigt;
	}

	public void setGenehmigt(boolean genehmigt) {
		this.genehmigt = genehmigt;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public String getTypuuid() {
		return typuuid;
	}

	public void setTypuuid(String typuuid) {
		this.typuuid = typuuid;
	}

	public String getUseruuid() {
		return useruuid;
	}

	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}

	public Abwesenheit toEntity(String organisationuuid) {
		Abwesenheit abwesenheit = new Abwesenheit();
		abwesenheit.setUuid(this.uuid);
		abwesenheit.setDatumende(LocalDate.parse(this.datumende));
		abwesenheit.setDatumstart(LocalDate.parse(this.datumstart));
		abwesenheit.setGenehmigt(this.genehmigt);
		abwesenheit.setKommentar(this.kommentar);
		abwesenheit.setOrganisationuuid(organisationuuid);
		abwesenheit.setTypuuid(this.typuuid);
		abwesenheit.setUseruuid(this.useruuid);
		abwesenheit.setVersion(this.version);
		return abwesenheit;
	}
	
	@Override
	public String toString() {
		return "useruuid=" 
				+ this.useruuid + "; typuuid=" + this.typuuid + "; uuid=" + this.uuid + "; kommentar=" + this.kommentar
				+ "; genehmigt=" + this.genehmigt + "; datumstart=" + this.datumstart + "; datumende=" + this.datumende;
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
		AbwesenheitDTO other = (AbwesenheitDTO) obj;
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

	public void setVersion(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}
}
