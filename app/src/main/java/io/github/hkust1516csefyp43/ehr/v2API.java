package io.github.hkust1516csefyp43.ehr;

import java.util.List;

import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Attachment;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.BlockedDevice;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.BloodType;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Clinic;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Country;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.DocumentType;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Gender;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Keyword;
import io.github.hkust1516csefyp43.ehr.pojo.server_response.v2.Patient;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Louis on 15/3/16.
 */
public interface v2API {

    interface attachments {
        @GET("v2/attachments")
        Call<List<Attachment>> getAttachments(
                @Header("token") String token,
                @Query("cloudinary_url") String url,
                @Query("file_name") String name,
                @Query("user_id") String userId,
                @Query("offset") Integer offset,
                @Query("sort_by") String sortBy,
                @Query("limit") Integer limit
        );

        @GET("v2/attachments/{id}")
        Call<Attachment> getAttachment(
                @Header("token") String token,
                @Path("id") String id
        );

        @POST("v2/attachments/")
        Call<Attachment> addAttachment(
                @Header("token") String token,
                @Body Attachment attachment
        );

        @PUT("v2/attachments/{id}")
        Call<Attachment> editAttachment(
                @Header("token") String token,
                @Path("id") String id,
                @Body Attachment attachment
        );

        @DELETE("v2/attachments/{id}")
        Call<Attachment> deleteAttachment(
                @Header("token") String token,
                @Path("id") String id
        );
    }

    interface blockedDevices {
        @GET("v2/blocked_devices")
        Call<List<BlockedDevice>> getBlockedDevices(
                @Header("token") String token,
                @Query("reporter_id") String reporterId,
                @Query("victim_id") String victimId,
                @Query("remark") String remark,
                @Query("offset") Integer offset,
                @Query("sort_by") String sortBy,
                @Query("limit") Integer limit
        );

        @GET("v2/blocked_devices/{id}")
        Call<BlockedDevice> getBlockedDevice(
                @Header("token") String token,
                @Path("id") String id
        );

        @POST("v2/blocked_devices/")
        Call<BlockedDevice> addBlockedDevice(
                @Header("token") String token,
                @Body BlockedDevice blockedDevice
        );

        @PUT("v2/blocked_devices/{id}")
        Call<BlockedDevice> editBlockedDevice(
                @Header("token") String token,
                @Path("id") String id,
                @Body BlockedDevice blockedDevice
        );

        @DELETE("v2/blocked_devices/{id}")
        Call<BlockedDevice> deleteBlockedDevice(
                @Header("token") String token,
                @Path("id") String id
        );
    }

    interface bloodTypes {
    }

    @GET("v2/blood_types")
    Call<List<BloodType>> getBloodTypes(
            @Header("token") String token,
            @Query("blood_type") String bloodType
    );

    @GET("v2/blood_types/{id}")
    Call<BloodType> getBloodType(
            @Header("token") String token,
            @Path("id") String bloodTypeId
    );

    @POST("v2/blood_types/")
    Call<BloodType> addBloodType(@Header("token") String token);

    @PUT("v2/blood_types/{id}")
    Call<BloodType> editBloodType(@Header("token") String token);

    @DELETE("v2/blood_types/{id}")
    Call<BloodType> deleteBloodType(@Header("token") String token);

    interface clinics {
    }

    /**
     * Get list of clinics without token >> clinic_id & english_name only
     * No param/header/query/body needed
     * @return
     */
    @GET("v2/clinics")
    Call<List<Clinic>> getSimplifiedClinics();

    /**
     * Get list of clinics
     * @param token >> access token
     * @return
     */
    @GET("v2/clinics")
    Call<List<Clinic>> getClinics(
            @Header("token") String token,
            @Query("country_id") String countryId,
            @Query("is_active") Boolean isActive,
            @Query("english_name") String englishName,
            @Query("native_name") String nativeName,
            @Query("latitude") Double latitude,
            @Query("longitude") Double longitude,
            @Query("create_timestamp") String createTimestamp,
            @Query("remark") String remark,
            @Query("is_global") Boolean isGlobal,
            @Query("suitcase_id") String suitcaseId,
            @Query("offset") Integer offset,
            @Query("sort_by") String sortBy,
            @Query("limit") Integer limit
    );

    @GET("v2/clinics/{id}")
    Call<Clinic> getClinic(
            @Header("token") String token,
            @Path("id") String id
    );

    interface countries {
        @GET("v2/countries")
        Call<List<Country>> getCountries(
                @Header("token") String token,
                @Query("english_name") String englishName,
                @Query("native_name") String nativeName,
                @Query("phone_country_code") Integer phoneCountryCode,
                @Query("phone_number_syntax") String phoneNumberSyntax,
                @Query("emoji") String emoji,
                @Query("offset") Integer offset,
                @Query("sort_by") String sortBy,
                @Query("limit") Integer limit
        );

        @GET("v2/countries/{id}")
        Call<Country> getCountry(
                @Header("token") String token,
                @Path("id") String id
        );

        @POST("v2/countries/")
        Call<Country> addCountry(
                @Header("token") String token,
                @Body Country country
        );

        @PUT("v2/countries/{id}")
        Call<Country> editCountry(
                @Header("token") String token,
                @Path("id") String id,
                @Body Country country
        );

        @DELETE("v2/countries/{id}")
        Call<Country> deleteCountry(
                @Header("token") String token,
                @Path("id") String id
        );
    }

    interface documentTypes {
        @GET("v2/document_types")
        Call<List<DocumentType>> getDocumentTypes(
                @Header("token") String token,
                @Query("type") String type,
                @Query("offset") Integer offset,
                @Query("sort_by") String sortBy,
                @Query("limit") Integer limit
        );

        @GET("v2/document_types/{id}")
        Call<DocumentType> getDocumentType(
                @Header("token") String token,
                @Path("id") String id
        );

        @POST("v2/document_types/")
        Call<DocumentType> addDocumentType(
                @Header("token") String token,
                @Body DocumentType documentType
        );

        @PUT("v2/document_types/{id}")
        Call<DocumentType> editDocumentType(
                @Header("token") String token,
                @Path("id") String id,
                @Body DocumentType documentType
        );

        @DELETE("v2/document_types/{id}")
        Call<DocumentType> deleteDocumentType(
                @Header("token") String token,
                @Path("id") String id
        );
    }

    interface genders {
        @GET("v2/genders")
        Call<List<Gender>> getGenders(
                @Header("token") String token
        );

        @POST("v2/genders/")
        Call<Gender> addGender(
                @Header("token") String token
        );

        @PUT("v2/genders/{id}")
        Call<Gender> editGender(
                @Header("token") String token,
                @Path("id") String id,
                @Body Gender gender
        );

        @DELETE("v2/genders/{id}")
        Call<Gender> deleteGender(
                @Header("token") String token,
                @Path("id") String id
        );
    }

    interface keywords {
        @GET("v2/keywords")
        Call<List<Keyword>> getKeywords(
                @Header("token") String token,
                @Query("keyword") String keyword,
                @Query("chief_complain") Boolean cc,
                @Query("diagnosis") Boolean diagnosis,
                @Query("screening") Boolean screening,
                @Query("allergen") Boolean allergen,
                @Query("follow-up") Boolean followUp,
                @Query("advice") Boolean advice,
                @Query("education") Boolean education,
                @Query("general") Boolean general,
                @Query("medication_route") Boolean medicationRoute,
                @Query("medication_form") Boolean medicationForm,
                @Query("medication_frequency") Boolean medicationFrequency,
                @Query("unit") Boolean unit,
                @Query("investigation") Boolean investigation,
                @Query("relationship_type") Boolean relationshipType
        );

        @GET("v2/keywords/{id}")
        Call<Keyword> getKeyword(
                @Header("token") String token,
                @Path("id") String id
        );

        @POST("v2/keywords/")
        Call<Keyword> addKeyword(
                @Header("token") String token,
                @Body Keyword keyword
        );

        @PUT("v2/keywords/{id}")
        Call<Keyword> editKeyword(
                @Header("token") String token,
                @Path("id") String id,
                @Body Keyword keyword
        );

        @DELETE("v2/keywords/{id}")
        Call<Keyword> deleteKeyword(@Header("token") String token);
    }

    /**
     * TODO age, age_ot and age_yt
     */
    @GET("v2/patients/")
    Call<List<Patient>> getPatients(
            @Header("token") String token,                                       //TODO replace Query with Header later
            @Query("clinic_id") String client,
            @Query("next_station") String nextStation,
            @Query("gender_id") String gender,
            @Query("phone_number_country_code") String phoneCountryCode,        //phone number is missing?
            @Query("email") String email,
            @Query("first_name") String firstName,
            @Query("middle_name") String middleName,
            @Query("last_name") String lastName,                                 //TODO add "name"
            @Query("name") String name,
            @Query("visit_date") String visitDate
    );

}
