package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Photo;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PhotoRepository implements IPhotoRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Photo> photo = new ConcurrentHashMap<Long, Photo>();
    @Override
    public Collection<Photo> getAll() {
        return this.photo.values();
    }

    @Override
    public Photo create(Photo photo) {
        Long id = photo.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            photo.setId(id);
        }

        this.photo.put(id, photo);
        return photo;
    }

    @Override
    public Photo getById(Long id) {
        return this.photo.get(id);
    }

    @Override
    public Photo update(Photo photo) {
        Long id = photo.getId();

        if (id != null) {
            this.photo.put(id, photo);
        }

        return photo;
    }

    @Override
    public void delete(Long id) {
        this.photo.remove(id);
    }
}
