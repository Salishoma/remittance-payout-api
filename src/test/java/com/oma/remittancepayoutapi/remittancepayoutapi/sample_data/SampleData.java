package com.oma.remittancepayoutapi.remittancepayoutapi.sample_data;

public class SampleData {

    public static String jsonData = """
            {
                "publickey": "PK_irikUjmtoWQxMuBLhqNuEJT1pe29BElY",
                "transaction": {
                    "reference": "83589862ID"
                },
                "order": {
                    "amount": "1.00",
                    "description": "",
                    "reason": " ",
                    "currency": "USD",
                    "country": "NG"
                },
                "source": {
                    "operation": "acct_payout",
                    "sender": {
                        "name": "niyi s komndi",
                        "address": "5 maryland",
                        "mobile": "0802154785",
                        "country": "NG",
                        "idtype": "PASSPORT",
                        "idnumber": "P567839222",
                        "idexpiry": "05-2019",
                        "occupation": "Pilot"
                    },
                    "recipient": {
                        "name": "charles ray",
                        "address": "3 ship yard",
                        "accountnumber": "0668349049",
                        "bankcode": "000003",
                        "beneOccupation": "Engineer",
                        "beneCustRelationship": "Brother"
                    }
                }
            }""";

}
