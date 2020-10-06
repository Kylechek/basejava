package com.javaops.webapp;

import com.javaops.webapp.model.*;

import java.time.YearMonth;

public class ResumeTestData {
        public static Resume fillOutResume(String uuid, String fullname){
            Resume resume = new Resume(uuid, fullname);
            resume.addContact(ContactType.TEL, "+7(921) 855-0482");
            resume.addContact(ContactType.SKYPE, "grigory.kislin");
            resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
            resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
            resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
            resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
            resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

            resume.addSection(SectionType.PERSONAL, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
            resume.addSection(SectionType.OBJECTIVE, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
            resume.addSection(SectionType.ACHIEVEMENT, new ListSection("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                    "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                    "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                    "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                    "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                    "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
            resume.addSection(SectionType.QUALIFICATIONS, new ListSection("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));
            resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("Java Online Projects", "http://javaops.ru", new Organization.Position("Автор проекта.",
                    "Создание, организация и проведение Java онлайн проектов и стажировок.", YearMonth.of(2013, 10), YearMonth.now())),
                    new Organization("Java Online Projects", "http://javaops.ru", new Organization.Position("Автор проекта.",
                            "Создание, организация и проведение Java онлайн проектов и стажировок.", YearMonth.of(2013, 10), YearMonth.now()))));
            resume.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("Coursera", "https://www.coursera.org/course/progfun", new Organization.Position("Functional Programming Principles in Scala by Martin Odersky", "", YearMonth.of(2013, 03), YearMonth.of(2013, 05)))));

            System.out.println(resume.getContacts());
            System.out.println(resume.getSections(SectionType.PERSONAL));
            System.out.println(resume.getSections(SectionType.OBJECTIVE));
            System.out.println(resume.getSections(SectionType.ACHIEVEMENT));
            System.out.println(resume.getSections(SectionType.QUALIFICATIONS));
            System.out.println(resume.getSections(SectionType.EXPERIENCE));
            System.out.println(resume.getSections(SectionType.EDUCATION));
            return resume;
    }
}