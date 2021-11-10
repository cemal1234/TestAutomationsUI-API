import static spark.Spark.*;

public class general {
    public static void main(String[] args) {
        post("/add", "application/json", (request, response) -> {
            // BadRequest dönmek yerine "status:false" olarak dönüyoruz.
            response.type("application/json");
            return Process("add", request);
        }, new JsonTransformer());

        post("/subtraction", "application/json", (request, response) -> {
            response.type("application/json");
            return Process("subtraction", request);
        }, new JsonTransformer());

        post("/multiplication", "application/json", (request, response) -> {
            response.type("application/json");
            return Process("multiplication", request);
        }, new JsonTransformer());

        post("/division", "application/json", (request, response) -> {
            response.type("application/json");
            return Process("division", request);
        }, new JsonTransformer());

        get("/sum", "application/json", (request, response) -> {
            response.type("application/json");
            return Process("sum", request);
        }, new JsonTransformer());

        get("/", "application/json", (request, response) -> {
            response.type("application/json");
            return "Hoş geldiniz";
        }, new JsonTransformer());
    }

    static MyResponse Process(String process, spark.Request req)
    {
        String _params =req.queryParams("params");
        String user = req.headers("user");
        String pass = req.headers("pass");

        if(user == null || pass == null)
            return new MyResponse(-1, "Belirlenemedi!", false, "Header eksik!");

        if(_params == null)
            return new MyResponse(-1, user, false, "Parametre eksik!");

        if(_params.split(",").length > 5)
            return new MyResponse(-1, user, false, "Parametre uzun!");

        try
        {
            int deger = 0;
            switch (process)
            {
                case "add":
                    for (String p: _params.split(",")) {
                        deger += Integer.parseInt(p);
                    }
                    break;

                case "subtraction":
                    String[] degerler = _params.split(",");
                    deger = Integer.parseInt(degerler[0]) - Integer.parseInt(degerler[1]);
                    break;

                case "multiplication":
                    deger = 1;
                    for (String p: _params.split(",")) {
                        deger = deger * Integer.parseInt(p);

                    }
                    break;

                case "division":
                    String[] degerler2 = _params.split(",");
                    deger = Integer.parseInt(degerler2[0]) / Integer.parseInt(degerler2[1]);
                    break;

                case "sum":
                    for (int i = 1; i <= Integer.parseInt(_params); i++)
                        deger += i;
                    break;
            }
            return new MyResponse(deger, user, true, "Sonuç başarılı");
        }
        catch(Exception ex)
        {
            return new MyResponse(-1, user, false, "İstisnai durum oluştu!");
        }
    }

    public static class MyResponse
    {
        public int result;
        public String user;
        public boolean status;
        public String message;

        public MyResponse(int _result, String _user, boolean _status, String _message)
        {
            this.result = _result;
            this.status = _status;
            this.user = _user;
            this.message = _message;
        }
    }
}