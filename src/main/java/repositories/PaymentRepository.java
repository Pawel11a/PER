package repositories;

import model.Payment;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface PaymentRepository extends CrudGenericRepository<Payment, BigInteger> {
}
