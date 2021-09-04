package onlineshopapp.fashionstore.junit;
import onlineshopapp.fashionstore.model.Voucher;
import onlineshopapp.fashionstore.repository.VoucherRepository;
import onlineshopapp.fashionstore.service.impl.VoucherServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class VoucherTest {

    @Mock
    private VoucherRepository voucherRepository;

    private VoucherServiceImpl service;

    @Before
    public void init() {

        MockitoAnnotations.initMocks(this);

        Voucher v = new Voucher();
        v.setCode((long) 100);

        Mockito.when(this.voucherRepository.save(Mockito.any(Voucher.class)))
                .thenReturn(v);

        Mockito.when(this.voucherRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(v));

        this.service = Mockito.spy(new VoucherServiceImpl(this.voucherRepository));
    }

    @Test
    public void foundByIdTest() {

        Voucher v = new Voucher();
        v.setCode((long) 100);

        Assert.assertEquals(Optional.of(v), service.findById(Mockito.anyLong()));
    }

    @Test
    public void notFoundByIdTest() {

        Assert.assertEquals(Optional.empty(), service.findById(null));
    }

    @Test
    public void createTest() {

        Voucher v = new Voucher();
        v.setCode((long) 100);

        Assert.assertEquals(v, service.create("name", 10));
    }

}
