Scenario: Test case for transaction pre-authorization

Given a transaction [merchantid=gateway_test, amount=1.00, currency=EUR, orderid=1234-123456789-4321, language=de, gender=, firstname=Max, lastname=Muster Mann, street=Hanauer Landstrasse, zip=60322, city=Frankfurt, country=DEU, company=Inatec, email=aneu@natec.com, customerip=127.1.1.1, payment_method=1, ccn=5232050000010003, cvc_code=003, cardholder_name=Muster Mann, exp_month=12, exp_year=2018]
When pre-authorize
Then transaction has been pre-authorized