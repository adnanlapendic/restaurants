package services;

import models.Location;
import play.Logger;
import repository.LocationRepository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lapa on 6/14/17.
 */
public class LocationService implements BaseService {

    private LocationRepository locationRepository;

    @Inject
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List getAllLocations(){
        return locationRepository.findAll();
    }

    public Location saveNewLocation(Location location) {
        return locationRepository.create(location);
    }

    public Location editLocation(Location location) {
        return locationRepository.update(location);
    }

    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id);
        locationRepository.delete(location);
    }

    public Location getLocationDetails(Long id) {
        Location location = locationRepository.findById(id);
        return location;
    }

}
