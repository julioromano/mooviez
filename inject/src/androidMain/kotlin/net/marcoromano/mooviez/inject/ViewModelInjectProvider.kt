package net.marcoromano.mooviez.inject

// TODO: Uncomment after having imported compose-multiplatform

// import androidx.compose.runtime.Composable
// import androidx.lifecycle.AbstractSavedStateViewModelFactory
// import androidx.lifecycle.SavedStateHandle
// import androidx.lifecycle.ViewModel
// import androidx.lifecycle.ViewModelStoreOwner
// import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
//
// @Composable
// public inline fun <reified VM : ViewModel> viewModel(
//  viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
//    "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
//  },
//  key: String? = null,
//  crossinline factory: (handle: SavedStateHandle) -> VM,
// ): VM = androidx.lifecycle.viewmodel.compose.viewModel(
//  viewModelStoreOwner = viewModelStoreOwner,
//  key = key,
//  factory = object : AbstractSavedStateViewModelFactory() {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(
//      key: String,
//      modelClass: Class<T>,
//      handle: SavedStateHandle,
//    ): T = factory(handle) as T
//  },
// )
