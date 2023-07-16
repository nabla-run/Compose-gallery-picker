package run.nabla.gallerypicker.picker.result

import androidx.compose.ui.graphics.Color

class GalleryRequest internal constructor() {

    var backgroundColor: Long = Color.Black.value.toLong()
        internal set
    var titleColor: Long = Color.White.value.toLong()
        internal set

    var title: String = ""
        internal set

    var titleSize: Int = 20
        internal set

    var showBackButton: Boolean = true
        internal set

    /**
     * A builder for constructing [GalleryRequest] instances.
     */
    class Builder {

        private var backgroundColor: Long = Color.Black.value.toLong()

        private var titleColor: Long = Color.White.value.toLong()

        private var titleSize: Int = 20

        private var title: String = ""

        private var showBackButton: Boolean = true

        /**
         * Set the background color for the screen.
         *
         * @param color the background color to set.
         * @return This builder.
         */
        fun setBackgroundColor(color: Long): Builder {
            this.backgroundColor = color
            return this
        }

        /**
         * Set the title color for the screen.
         *
         * @param color the title color to set.
         * @return This builder.
         */
        fun setTitleColor(color: Long): Builder {
            this.titleColor = color
            return this
        }

        /**
         * Set the title size for the screen.
         *
         * @param size the title size to set.
         * @return This builder.
         */
        fun setTitleSize(size: Int): Builder {
            this.titleSize = size
            return this
        }

        /**
         * Set the header title for the [GalleryRequest].
         *
         * @param title header title to go into the GalleryRequest
         * @return This builder.
         */
        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        /**
         * Set whether to show the back button on the screen.
         *
         * @param show true to show the back button, false otherwise.
         * @return This builder.
         */
        fun setShowBackButton(show: Boolean): Builder {
            this.showBackButton = show
            return this
        }

        /**
         * Build the GalleryRequest specified by this builder.
         *
         * @return the newly constructed GalleryRequest.
         */
        fun build(): GalleryRequest = GalleryRequest().apply {
            this.backgroundColor = this@Builder.backgroundColor
            this.titleColor = this@Builder.titleColor
            this.title = this@Builder.title
            this.titleSize = this@Builder.titleSize
            this.showBackButton = this@Builder.showBackButton
        }
    }
}
