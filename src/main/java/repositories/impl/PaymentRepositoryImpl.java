package repositories.impl;

import model.Payment;
import repositories.PaymentRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class PaymentRepositoryImpl extends AbstractCrudGenericRepository<Payment, BigInteger> implements PaymentRepository {
}
