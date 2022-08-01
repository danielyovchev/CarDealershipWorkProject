# CarDealershipWorkProject
Система за автокъща. Ще има сървис, който извършва CRUD операции по базата данни. Попълването ще се случва, чрез информация получена от външно апи. Ще има сървис за бизнес логиката, свързан с продажбата на автомобили, както и сървис свързан с образуването на заплата и бонус на даден служител.
Операции:
- въвеждана на кола в базата данни(по VIN)
- промяна - промяна на детайл(пребоядисване)
- извличане на кола според характеристика(извеждаме всички коли, които отговарят на даден параметър(цвят или гориво например))
 или според статус(продадена или налична)
- продаване на кола - в брой или на лизинг(изчисляване на крайната продажна цена в зависимост от типа сделка,
 добавяме сделката в таблица продажба)
- служители - сортиране по брой продажби
- изчисляване на бонуси на служител според вида продажба и крайната цена
- изчисляване на заплатата на служител за даден месец
- външен сървис за изчисляване на цената
