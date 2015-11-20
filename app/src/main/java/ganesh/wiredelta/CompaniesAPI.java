package ganesh.wiredelta;


import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface CompaniesAPI {

    @GET("/bins/2ggcs")
    public void getBooks(Callback<List<Company>> response);
}