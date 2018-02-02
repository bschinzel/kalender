package de.schinzel.abwesenheit.rest.v1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import de.schinzel.abwesenheit.metrics.Metrics;
import de.schinzel.abwesenheit.model.Abwesenheit;
import de.schinzel.abwesenheit.model.AbwesenheitDTO;

@Path("/v1")
@Stateless
@LocalBean
public class AbwesenheitEndpoint {
	
	private static final Logger LOGGER = Logger.getLogger(AbwesenheitEndpoint.class); 
	private final Timer requestsGetAlle = Metrics.REGISTRY.timer(
			MetricRegistry.name(AbwesenheitEndpoint.class, "GET /abwesenheiten/v1/ ?organisationuuid"));
	private final Timer requestsGetTag = Metrics.REGISTRY.timer(
			MetricRegistry.name(AbwesenheitEndpoint.class, "GET /abwesenheiten/v1/ ?organisationuuid&jahr&tag"));
	private final Timer requestsPostAbwesenheit = Metrics.REGISTRY.timer(
			MetricRegistry.name(AbwesenheitEndpoint.class, "POST /abwesenheiten/v1/"));
	private final Timer requestsDeleteAbwesenheit = Metrics.REGISTRY.timer(
			MetricRegistry.name(AbwesenheitEndpoint.class, "DELETE /abwesenheiten/v1/{uuid}"));
	private final Timer requestsGetMonat = Metrics.REGISTRY.timer(
			MetricRegistry.name(AbwesenheitEndpoint.class, "GET /abwesenheiten/v1/ ?organisationuuid&jahr&monat"));
	private final Timer requestsGetJahr = Metrics.REGISTRY.timer(
			MetricRegistry.name(AbwesenheitEndpoint.class, "GET /abwesenheiten/v1/ ?organisationuuid&jahr"));
	
	@PersistenceContext private EntityManager em;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@NoCache
	@Transactional(Transactional.TxType.SUPPORTS)
	public List<AbwesenheitDTO> alleAbwesenheitenEinerOrganisation(
			@QueryParam("organisationuuid") String organisationuuid) {
		
		final Timer.Context timercontext = requestsGetAlle.time();
		List<Abwesenheit> abwesenheiten = null;
		LOGGER.trace("GET organisationuuid=" + organisationuuid);
		try {
			abwesenheiten = this.em.createNamedQuery("alleAbwesenheitenEinerOrganisation", Abwesenheit.class)
				.setParameter("organisationuuid", organisationuuid)
				.getResultList();
			List<AbwesenheitDTO> abwesenheitenDTOs = new ArrayList<AbwesenheitDTO>(abwesenheiten.size());
			abwesenheiten.forEach(a -> abwesenheitenDTOs.add(a.toDTO()));
			return abwesenheitenDTOs;
		} catch (Exception e) {
			LOGGER.error("GET organisationuuid=" + organisationuuid + "fehlgeschlagen");
			throw e;
		} finally {
			timercontext.stop();
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@NoCache
	@Transactional(Transactional.TxType.SUPPORTS)
	public List<AbwesenheitDTO> alleAbwesenheitenInEinemJahr(
			@QueryParam("organisationuuid") String organisationuuid, 
			@QueryParam("jahr") int jahr) {
		
		final Timer.Context timercontext = requestsGetJahr.time();
		List<Abwesenheit> abwesenheiten = null;
		LOGGER.trace("GET organisationuuid=" + organisationuuid + "; jahr=" + jahr);
		try {
			abwesenheiten = this.em.createNamedQuery("alleAbwesenheitenEinerOrganisationEinesJahres", Abwesenheit.class)
				.setParameter("organisationuuid", organisationuuid)
				.setParameter("jahrbeginn", jahr)
				.getResultList();
			List<AbwesenheitDTO> abwesenheitenDTOs = new ArrayList<AbwesenheitDTO>(abwesenheiten.size());
			abwesenheiten.forEach(a -> abwesenheitenDTOs.add(a.toDTO()));
			return abwesenheitenDTOs;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			timercontext.stop();
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@NoCache
	@Transactional(Transactional.TxType.SUPPORTS)
	public List<AbwesenheitDTO> alleAbwesenheitenInEinemMonat(
			@QueryParam("organisationuuid") String organisationuuid, 
			@QueryParam("jahr") int jahr,
			@QueryParam("monat") int monat) {
		
		final Timer.Context timercontext = requestsGetMonat.time();
		List<Abwesenheit> abwesenheiten = null;
		LOGGER.trace("GET organisationuuid=" + organisationuuid + "; jahr=" + jahr + "; monat=" + monat);
		try {
			abwesenheiten = this.em.createNamedQuery("alleAbwesenheitenEinerOrganisationEinesMonats", Abwesenheit.class)
				.setParameter("organisationuuid", organisationuuid)
				.setParameter("jahrbeginn", jahr)
				.setParameter("monatbeginn", monat)
				.getResultList();
			List<AbwesenheitDTO> abwesenheitenDTOs = new ArrayList<AbwesenheitDTO>(abwesenheiten.size());
			abwesenheiten.forEach(a -> abwesenheitenDTOs.add(a.toDTO()));
			return abwesenheitenDTOs;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			timercontext.stop();
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@NoCache
	@Transactional(Transactional.TxType.SUPPORTS)
	public List<AbwesenheitDTO> alleAbwesenheitenAnEinemTag(
			@QueryParam("organisationuuid") String organisationuuid, 
			@QueryParam("jahr") int jahr,
			@QueryParam("monat") int monat, 
			@QueryParam("tag") int tag) {
		final Timer.Context timercontext = requestsGetTag.time();
		List<Abwesenheit> abwesenheiten = null;
		LOGGER.trace("GET organisationuuid=" + organisationuuid + "; jahr=" + jahr + "; monat=" + monat + "; tag =" + tag);
		try {
			abwesenheiten = this.em.createNamedQuery("alleAbwesenheitenEinerOrganisationEinesDatums", Abwesenheit.class)
				.setParameter("organisationuuid", organisationuuid)
				.setParameter("datum", LocalDate.of(jahr, monat, tag))
				.getResultList();
			List<AbwesenheitDTO> abwesenheitenDTOs = new ArrayList<AbwesenheitDTO>(abwesenheiten.size());
			abwesenheiten.forEach(a -> abwesenheitenDTOs.add(a.toDTO()));
			return abwesenheitenDTOs;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		} finally {
			timercontext.stop();
		}
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional(Transactional.TxType.REQUIRED)
	public void erstelleAntrag(AbwesenheitDTO abwesenheit) { // TODO korrelator
		final Timer.Context timercontext = requestsPostAbwesenheit.time();
		LOGGER.tracef("POST %s", abwesenheit.toString());
		try {
			this.em.merge(abwesenheit.toEntity("1")); // TODO header auth
		} catch (Exception e) {
			LOGGER.errorf("POST %s fehlgeschlagen", abwesenheit.toString());
			throw e;
		} finally {
			timercontext.stop();
		}
		// TODO weiterleiten an anderen pfad
	}
	
	@DELETE
	@Path("/{uuid}")
	@Produces(MediaType.TEXT_PLAIN)
	@Transactional(Transactional.TxType.REQUIRED)
	public void entferneAntrag(@PathParam("uuid") String uuid) { // TODO korrelator
		final Timer.Context timercontext = requestsDeleteAbwesenheit.time();
		LOGGER.tracef("DELETE %s", uuid.toString());
		try {
			int count = this.em.createNamedQuery("loescheAbwesenheit")
					.setParameter("uuid", uuid)
					.executeUpdate();
			
			if (count != 0) {
				LOGGER.tracef("DELETE %s erfolgreich", uuid.toString());
			} else {
				LOGGER.warnf("DELETE %s ist gescheitert, weil die UUID nicht gefunden wurde", 
						uuid.toString());
			}
		} catch (Exception e) {
			LOGGER.errorf("DELETE %s ist gescheitert", uuid);
			throw e;
		} finally {
			timercontext.stop();
		}
		// TODO weiterleiten an anderen pfad
	}
}
