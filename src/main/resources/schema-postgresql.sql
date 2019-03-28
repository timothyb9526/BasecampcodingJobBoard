CREATE TABLE IF NOT EXISTS employers (
    id UUID PRIMARY KEY,
    name TEXT,
    companyName TEXT,
    logo Text,
    city TEXT,
    state TEXT,
    country TEXT,
    position TEXT,
    description TEXT,
    benefits TEXT,
    date TEXT,
    responsibilities TEXT,
    qualifications TEXT ,
    preferredQualifications TEXT
);

CREATE TABLE IF NOT EXISTS comments (
    id UUID PRIMARY KEY,
    employer_id UUID references employers (id),
    name TEXT,
    status TEXT,
    date TEXT,
    comment TEXT
);
