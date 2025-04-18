import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.startseva.controllers.CatController;
import ru.startseva.controllers.OwnerController;
import ru.startseva.dao.CatDAO;
import ru.startseva.dao.OwnerDAO;
import ru.startseva.dtos.CatDto;
import ru.startseva.dtos.OwnerDto;
import ru.startseva.models.Cat;
import ru.startseva.models.CatColor;
import ru.startseva.models.Owner;
import ru.startseva.services.OwnerService;
import ru.startseva.services.OwnerServiceImpl;
import ru.startseva.services.CatService;
import ru.startseva.services.CatServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CatTests {
    @Mock
    private CatDAO catDAO;

    @Mock
    private OwnerDAO catOwnerDAO;

    private CatService catService;
    private OwnerService ownerService;
    private CatController catController;
    private OwnerController ownerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.catService = new CatServiceImpl(catDAO);
        this.ownerService = new OwnerServiceImpl(catOwnerDAO, catDAO);
        this.catController = new CatController(catService);
        this.ownerController = new OwnerController(ownerService);
    }

    @Test
    public void testAllCats() {
        List<Cat> expectedCats = new ArrayList<>();
        Cat cat1 = new Cat("Kitty", LocalDate.now(), "Persian", CatColor.Black);
        Cat cat2 = new Cat("Tom", LocalDate.now(), "Siamese", CatColor.White);
        cat1.setCatID(1);
        cat2.setCatID(2);
        expectedCats.add(cat1);
        expectedCats.add(cat2);
        when(catDAO.getAllCats()).thenReturn(expectedCats);

        List<CatDto> actualCats = catController.getAllCats();

        assertEquals(expectedCats.size(), actualCats.size());
        assertEquals(cat1.getName(), actualCats.get(0).getName());
        assertEquals(cat2.getName(), actualCats.get(1).getName());

        verify(catDAO, times(1)).getAllCats();
    }

    @Test
    public void testAddCatFriend() {
        Cat cat1 = new Cat("Kitty", LocalDate.now(), "Persian", CatColor.Black);
        Cat cat2 = new Cat("Tom", LocalDate.now(), "Siamese", CatColor.White);
        cat1.setCatID(1);
        cat2.setCatID(2);

        when(catDAO.getCatById(1)).thenReturn(cat1);
        when(catDAO.getCatById(2)).thenReturn(cat2);

        catController.addFriend(1, 2);


        verify(catDAO, times(1)).updateCat(cat1);
        verify(catDAO, times(1)).updateCat(cat2);
    }

    @Test
    public void testAllOwners() {
        List<Owner> expectedOwners = new ArrayList<>();
        Owner owner1 = new Owner("Emil", LocalDate.now());
        Owner owner2 = new Owner("Misha", LocalDate.now());
        owner1.setOwnerID(1);
        owner2.setOwnerID(2);
        expectedOwners.add(owner1);
        expectedOwners.add(owner2);
        when(catOwnerDAO.getAllOwners()).thenReturn(expectedOwners);

        List<OwnerDto> actualOwners = ownerController.getAllOwners();

        assertEquals(expectedOwners.size(), actualOwners.size());
        assertEquals(owner1.getName(), actualOwners.get(0).getName());
        assertEquals(owner2.getName(), actualOwners.get(1).getName());

        verify(catOwnerDAO, times(1)).getAllOwners();
    }
}
