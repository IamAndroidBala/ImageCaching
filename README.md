# ImageCaching
<h1>Short Description</h1>
<p>This app Caching the image into external storage <br/> Here i dont use any third party like <strong>Glide</strong> or <strong>Picasso</strong> for caching images </p1>

I have used <Strong> Dagger 2 </Strong> and <Strong> Retrofit 2 </Strong> for Dependancy injection and Network API calls 


<h2>How to use in app<h2>
  
```
val imageLoader = ImageLoader(this)
imageLoader.displayImage(imageUrl, target, progressBar)
```
<h2>Add in your app<h2>
  
  ```
  implementation 'com.github.IamAndroidBala:ImageCaching:V1.0'
  ```

To cache the api response, i have created OkHttpClient with cache request, So that retrofit can cahce the response in cache

That's it.
